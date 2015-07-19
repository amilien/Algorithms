package interviews.atlassian.findcommonancestor;

import java.util.HashSet;
import java.util.Set;

public class MyFindCommonAncestor implements FindCommonAncestor {
	
    public String findCommmonAncestor(String[] commitHashes, String[][] parentHashes, String commitHash1, String commitHash2) {
        boolean[] commitFlags1 = getFlagsForCommits(commitHash1, commitHashes, parentHashes);
        boolean[] commitFlags2 = getFlagsForCommits(commitHash2, commitHashes, parentHashes);
        for (int i = 0; i < commitHashes.length; i++) {
            if (commitFlags1[i] && commitFlags2[i])
                return commitHashes[i];
        }
        return null;
    }

    private void buildPathFromRootToCommitHash(Set<String> path, int startPos, 
    		String commitHash, String[] commitHashes, String[][] parentHashes) {
    	int len = commitHashes.length;
    	int i = startPos;
        for (; i < len; i++) {
            if (commitHashes[i].equals(commitHash)) {
            	path.add(commitHashes[i]);
                break;
            }
        }
        if (i < len && parentHashes[i] != null) {
            for (String parent: parentHashes[i]) {
                if (!path.contains(parent))
                	buildPathFromRootToCommitHash(path, i, parent, commitHashes, parentHashes);
            }
        }
    }

    private boolean[] getFlagsForCommits(String commitHash, String[] commitHashes, String[][] parentHashes) {
    	int len = commitHashes.length;
        boolean[] flags = new boolean[len];
        Set<String> commitPathSet = new HashSet<String>();
        buildPathFromRootToCommitHash(commitPathSet, 0, commitHash, commitHashes, parentHashes);
        for (int i = 0; i < len; i++)
            flags[i] = commitPathSet.contains(commitHashes[i]);
        return flags;
    }

}