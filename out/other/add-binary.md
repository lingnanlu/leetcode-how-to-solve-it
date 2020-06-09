#add binary

https://leetcode.com/problems/add-binary

### 问题描述

<p>Given two binary strings, return their sum (also a binary string).</p>

<p>The input strings are both <strong>non-empty</strong> and contains only characters <code>1</code> or&nbsp;<code>0</code>.</p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;11&quot;, b = &quot;1&quot;
<strong>Output:</strong> &quot;100&quot;</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;1010&quot;, b = &quot;1011&quot;
<strong>Output:</strong> &quot;10101&quot;</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>Each string consists only of <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code> characters.</li>
	<li><code>1 &lt;= a.length, b.length &lt;= 10^4</code></li>
	<li>Each string is either <code>&quot;0&quot;</code> or doesn&#39;t contain any leading zero.</li>
</ul>

### 解题思路

this is the solution

### 代码

```java
public class Add_Binary {

    public static void main(String[] args) {

        Add_Binary test = new Add_Binary();

        test.addBinary("1", "111");
    }

    public String addBinary(String a, String b) {

        Stack<Character> r = new Stack<Character>();

        int carry = 0;
        int i = a.length() - 1, j = b.length() - 1;

        while (i >= 0 && j >= 0) {
            int n = a.charAt(i) - '0' + b.charAt(j) - '0' + carry;

            if (n == 0) {
                r.push('0');
                carry = 0;
            } else if (n == 1) {
                r.push('1');
                carry = 0;
            } else if (n == 2) {
                r.push('0');
                carry = 1;
            } else {    // n = 3
                r.push('1');
                carry = 1;
            }

            i--;
            j--;
        }

        while(i >= 0) {
            int n = a.charAt(i) - '0' + carry;
            if (n == 0) {
                r.push('0');
                carry = 0;
            } else if (n == 1) {
                r.push('1');
                carry = 0;
            } else {
                r.push('0');
                carry = 1;
            }
            i--;
        }

        while(j >= 0) {
            int n = b.charAt(j) - '0' + carry;
            if (n == 0) {
                r.push('0');
                carry = 0;
            } else if (n == 1) {
                r.push('1');
                carry = 0;
            } else {
                r.push('0');
                carry = 1;
            }
            j--;
        }

        if (carry == 1) {
            r.push('1');
        }

        StringBuilder sb = new StringBuilder();
        while (!r.isEmpty()) {
            sb.append(r.pop());
        }

        return sb.toString();

    }
}
```
