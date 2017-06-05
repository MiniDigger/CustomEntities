package me.minidigger.customentities.tools;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class GenerateWiki {

    private static String gitUser, gitPass;
    private File outDir = new File("wiki");
    private Set<String> sidebar = new HashSet<>();
    private Git git;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("You did not specify user or pw for git, changes will not be pushed!");
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
}
