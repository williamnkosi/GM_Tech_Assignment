package nkosi.william.gmtechassignment.models;

public class Commit {
    String Author;
    String commitHash;
    String commitMessage;

    public Commit(String author, String commitHash, String commitMessage) {
        this.Author = author;
        this.commitHash = commitHash;
        this.commitMessage = commitMessage;
    }

    public String getAuthor() {
        return Author;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public String getCommitMessage() {
        return commitMessage;
    }
}
