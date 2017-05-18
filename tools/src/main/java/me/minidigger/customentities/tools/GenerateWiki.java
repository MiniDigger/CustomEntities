package me.minidigger.customentities.tools;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.reflections.Reflections;

import com.artemis.Component;

import me.minidigger.customentities.api.Documentation;

public class GenerateWiki {

    private File outDir = new File("wiki");

    private Set<String> sidebar = ConcurrentHashMap.newKeySet();

    private Git git;

    public static void main(String[] args) {
        new GenerateWiki().start();
    }

    public void start() {
        System.out.println("Starting...");
        if (outDir.exists())
            outDir.delete();

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
        outDir.delete();
    }

    public void commitWiki() {
        try {
            git.commit().setMessage("Bot Update " + new Date().toString()).setAuthor("bot", "bot@minidigger.me").call();
            // git.push().sert TODO PUSH
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
        new Reflections("me.minidigger").getSubTypesOf(Component.class).parallelStream().forEach(this::generateComponent);
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
        System.out.println("    Generate " + clazz.getSimpleName() + ".md");
        Documentation documentation = clazz.getAnnotation(Documentation.class);
        if (documentation == null)
            return;

        try (PrintWriter pw = new PrintWriter(new File(outDir, clazz.getSimpleName() + ".md"))) {
            pw.println(documentation.val() + ":  ");
            pw.println();
            for (Field field : clazz.getDeclaredFields()) {
                String doc = field.isAnnotationPresent(Documentation.class) ? ": " + field.getAnnotation(Documentation.class).val() : "";
                pw.println("* " + field.getName() + ": " + field.getType().getName() + doc);
            }

            sidebar.add("    * [[" + clazz.getSimpleName() + "|" + clazz.getSimpleName() + "]]");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
