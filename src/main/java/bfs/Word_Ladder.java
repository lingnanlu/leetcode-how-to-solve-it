package bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 */
// BFS有个硬伤，是无法记录变换路径的。
public class Word_Ladder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);
        int currentLevel = 1;       // 将要处理的层
        while(!queue.isEmpty()) {

            int levelSize = queue.size();
            // 每一次内循环处理一层，每一层一个一个的处理
            for (int i = 0; i < levelSize; i++) {
                String word = queue.poll();
                List<String> nextWords = selectNextWords(word, wordList);
                if (nextWords.contains(endWord)) {
                    return currentLevel + 1;
                } else {
                    queue.addAll(nextWords);
                }
            }

            currentLevel++;
        }

        return 0;
    }

    // 从dict中选择相近的词。选出后，删除dict中的词
    private List<String> selectNextWords(String word, List<String> dict) {
        List<String> nextWords = new LinkedList<>();

        for (String each : dict) {
            if (isOneCharDiff(word, each)) {
                nextWords.add(each);
            }
        }

        dict.removeAll(nextWords);
        return nextWords;
    }

    private boolean isOneCharDiff(String one, String another) {

        int diffCharCount = 0;
        for (int i = 0; i < one.length(); i++) {
            if (one.charAt(i) != another.charAt(i)) {
                diffCharCount++;
            }
        }

        return diffCharCount == 1;
    }

}
