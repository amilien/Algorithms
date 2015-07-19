package interviews.atlassian.findcommonancestor;

public interface FindCommonAncestor {
	String findCommmonAncestor(String[] commitHashes, String[][] parentHashes, String commitHash1, String commitHash2);
}
