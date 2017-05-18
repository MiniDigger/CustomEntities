package me.minidigger.customentities.tools;

import com.artemis.Component;
import me.minidigger.customentities.api.Documentation;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.util.FileUtils;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class GenerateWiki {

    private File outDir = new File("wiki");

    private Set<String> sidebar = new HashSet<>();

    private Git git;
    private static String gitUser, gitPass;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("You did not speficy user or pw for git, changes will not be pushed!");
        } else {
            gitUser = args[0];
            gitPass = args[1];
        }

        new GenerateWiki().start();
    }

    public void start() {
        System.out.println("Starting...");
        if (outDir.exists()) {
            System.out.println("Removing old wiki folder");
            try {
                FileUtils.delete(outDir, FileUtils.RECURSIVE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Cloning wiki...");
        cloneWiki();

        System.out.println("Generating components....");
        generateComponents();

        System.out.println("Generate sidebar...");
        generateSidebar();

        System.out.println("Commit wiki...");
        commitWiki();

        System.out.println("Cleanup...");
        git.close();
    }

    public void commitWiki() {
        try {
            git.add().addFilepattern(".").call();
            git.commit().setMessage("Bot Update " + new Date().toString()).setAuthor("bot", "bot@minidigger.me").call();
            if (gitUser != null) {
                git.push().setCredentialsProvider(new UsernamePasswordCredentialsProvider(gitUser, gitPass)).call();
            }
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    public void cloneWiki() {
        try {
            git = Git.cloneRepository().setURI("https://github.com/MiniDigger/CustomEntities.wiki.git").setDirectory(outDir).setProgressMonitor(new TextProgressMonitor(new PrintWriter(System.out)))
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    public void generateComponents() {
        new Reflections("me.minidigger").getSubTypesOf(Component.class).forEach(this::generateComponent);
    }

    public void generateSidebar() {
        try (PrintWriter pw = new PrintWriter(new File(outDir, "_Sidebar.md"))) {
            pw.println("# CustomEntities Wiki");
            pw.println();
            pw.println("* [[Home|home]]");
            pw.println("* [[Minimal Component Set|Minimal-Component-Set]]");
            pw.println("* [[Components|Components]]");
            sidebar.stream().sorted().forEachOrdered(pw::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void generateComponent(Class<? extends Component> clazz) {
        try {
            File file = new File(outDir, clazz.getSimpleName() + ".md");
            Documentation documentation = clazz.getAnnotation(Documentation.class);
            if (documentation == null) {
                return;
            }
            System.out.println("    Generate " + file.getAbsolutePath());

            try (PrintWriter pw = new PrintWriter(file)) {
                pw.println(documentation.value() + ":  ");
                pw.println();
                for (Field field : clazz.getDeclaredFields()) {
                    String doc = field.isAnnotationPresent(Documentation.class) ? ": " + field.getAnnotation(Documentation.class).value() : "";
                    pw.println("* " + field.getName() + ": " + field.getType().getName() + doc);
                }

                sidebar.add("    * [[" + clazz.getSimpleName() + "|" + clazz.getSimpleName() + "]]");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
