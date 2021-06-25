# Gas Station
https://leetcode.com/problems/gas-station/
## 一 What the question is ?
<p>There are <code>n</code> gas stations along a circular route, where the amount of gas at the <code>i<sup>th</sup></code> station is <code>gas[i]</code>.</p>

<p>You have a car with an unlimited gas tank and it costs <code>cost[i]</code> of gas to travel from the <code>i<sup>th</sup></code> station to its next <code>(i + 1)<sup>th</sup></code> station. You begin the journey with an empty tank at one of the gas stations.</p>

<p>Given two integer arrays <code>gas</code> and <code>cost</code>, return <em>the starting gas station&#39;s index if you can travel around the circuit once in the clockwise direction, otherwise return</em> <code>-1</code>. If there exists a solution, it is <strong>guaranteed</strong> to be <strong>unique</strong></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> gas = [1,2,3,4,5], cost = [3,4,5,1,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> gas = [2,3,4], cost = [3,4,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong>
You can&#39;t start at station 0 or 1, as there is not enough gas to travel to the next station.
Let&#39;s start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can&#39;t travel around the circuit once no matter where you start.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>gas.length == n</code></li>
	<li><code>cost.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= gas[i], cost[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 二 How to solve it ?

这题是一个模拟题，题中给出的是数组形式表示的圈，为了更直观一些，画一个圈，标上数组中的数值。
就是使用代码来模拟操作，关键是表述清晰


## 三 Related questions
## 四 Solutions 
### 1 null

这种方法其实是考察智力的一种方法，其实就是一种数学证明，但感觉这种方法没有参考意义，只能用在这里

```java
public int canCompleteCircuit(int[] gas, int[] cost) {

    int tank = 0;       //记录油箱储备

    int total = 0;      //如果最终total>=0 说明肯定有方案

    int start = 0;
    for(int i = 0; i < gas.length; i++) {

        total += gas[i] - cost[i];
        tank += gas[i] - cost[i];

        if(tank < 0) {
            //假如是从start开始, 此时就是第一次从i到达不了i+1了.
            //那么从start到i之间的任何一个开始, 也是无法到达i+1的,
            //举个例子, 比如, A, B, C, D, E, F , 从A开始能到B, C, D, E, 到不了F
            //那么从B, C, D, E, 开始是都达不了F的, 这是因为A能到B, 说明到B之后, 肯定还有剩余. 从A到不了, 那么, 从B也是到不了的
            //同理, 从C, D, E也是到不了的. 所以可以把这之间的statio全部排除了.
            tank = 0;
            start = i + 1;
        }
    }

    // 这里有一个假设, 如果total>=0 , 说明一定有解,
    return total >= 0 ? start : -1;
}
```
### 2 null

模拟的方法， 一个地，一个地的尝试

```java
public int canCompleteCircuit(int[] gas, int[] cost) {

    // 从0位置进行尝试。
    for (int start = 0; start < gas.length; start++) {

        // 剩余要走的步数。
        int stepRemained = gas.length;
        // 当前所在地
        int current = start;

        // 下一个目的地
        int next = (start + 1) % gas.length;

        int gasLeft = 0;

        while(stepRemained != 0) {

            // 走一步试试
            int left = canMoveToNext(gas, cost, current, gasLeft);

            if (left >= 0) {         // 说明能移动到下一地
                stepRemained--;
                current = next;
                next = (next + 1) % gas.length;
                gasLeft = left;
            } else {
                break;
            }
        }

        if(stepRemained == 0) {
            // 说明从start开始能走一圈
            return start;
        }

    }

    return -1;
}

/**
 * 能不能开到下一地
 * @param gas
 * @param cost
 * @param location 当前所在地
 * @param gasLeft   当前剩余汽油
 * @return 移动到下一地后的剩余汽油
 */
private int canMoveToNext(int[] gas, int[] cost, int location, int gasLeft) {
    return gasLeft + gas[location] - cost[location] ;
}
```
