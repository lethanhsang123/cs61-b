# 1 The Space-Time Continuum
Consider the following runtimes:  
$Log(n) + N$ &emsp;|&emsp; $Log(2^{N})$ &emsp;|&emsp; $N^{2} + 3NLog(N)$ &emsp;|&emsp; $2^{N} \times 2$ &emsp;|&emsp; $Log(9)$ &emsp;|&emsp; $2^{N+4}$ &emsp;|&emsp; $2 + \frac{1}{n}$ &emsp;|&emsp; $Log(N^{2})$ &emsp;|&emsp; $5 \times N^{2}$ &emsp;|&emsp; $6 \times Log(N)$  
Theta bound, sumplify, and sort each of the aboce runtimes in the below categories.  
$\Theta(1)$ $\Theta(Log(N))$ $\Theta(N)$ $\Theta(N^{2})$ $\Theta(2^{N})$  
slowest-growing <---------------------------------------> fastest-growing
## Description
### Understand what $\Theta$ means
Suppose an algorithm has runtime:  
$$R(N) = 3N^{2} + 5N + 10$$
What happens as $N$ becomes very large ?  
- $3N^{2}$ grows **quadratically**
- $5N$ grows **linearly**
- 10 is **constant**

Eventually, the quadratic term dominates the other terms. We thefore say:  
$$R(N) \in \Theta(N^{2})$$
The notation describe the ***asymptotic growth rate***, ignoring constant factor and lower order terms.  
Two important simplification rules follow:  
**Rule 1: Ignore positive constant multipliers.**
$$\Theta(5N^{2}) = \Theta(N^{2})$$
**Rule 2: In a sum of nonnegative terms, keep the fastest-growing term.**
$$\Theta(N^{2} + N) = \Theta(N^{2})$$
### Understand the five growth categories
Five growth categories, ordered from slowest-growing to fastest-growing  
- Constant $\Theta(1)$ Runtime does not grow with $N$
- Logarithmic $\Theta(Log(N)$ Growth is very slow
- Linear $\Theta(N)$ Growth is proportional to N
- Quadratic $\Theta(N^{2})$ Growth is proportional to the square of N
- Exponential $\Theta(2^{N})$ N appears in the exponent
### Learn the logarithm rules
#### Rule A: Power Rule
$$Log(N^{k}) = k \times Log(N)$$
An exponent inside the logarithm becomes a multiplier outside it.  
Example: $Log(N^{2}) = 2 \times Log(N)$  
Since $2$ is a constant multiplier: $Log(N^{2}) \in \Theta(Log(N))$
#### Rule B: Product Rule
$$Log(ab) = Log(a) + Log(b)$$
Example: $Log(2N) = Log(2) + Log(N)$  
Since $Log(2)$ is a constant, the constant disappears asymptotically: $Log(2N) \in \Theta(Log(N))$
#### Rule C: Change of base
Changing the logarithm's base only introduces a constant multiplier:  
$$Log_{a}(N) = \frac{Log_{b}(N)}{Log_{b}(a)}$$
For asymptotic analysis, ***the base does not change the Theta class***, provided the base is a fixed number greater than 1.  
So $Log_{2}(N), Log_{10}(N), Ln(N)$ all belong to $\Theta(Log(N))$
### Simplify exponential expressions
An exponential expression has the variable in the exponent.  
$$a^{x + y} = a^{x} + a^{y}$$
$$a^{x - y} = \frac{a^{x}}{a^{y}}$$
Example: $2^{N + 4} = 2^{N} + 2^{4}$  
Theta bound: $\Theta(2^{N + 4}) = \Theta(2^N)$
### Simplify sums and products
#### Addition: Keep the dominant term
Consider:$$R(N) = N^{2} + 3NLog(N)$$
Compate the terms:
- $N^{2}$ is quadratic.
- $NLog(N)$ grows faster than liner but slower than quadratic.

Thefore, $N^{2}$ dominates the sum:$$R(N) \in \Theta(N^{2})$$
#### Multiplication: Multiply the growth functions
Suppose: $$R(N) = N \times Log(N)$$
This is not a sum. *Both factors contribute to the growth*.  
The simplified expression is already $NLog(N)$ so it class is:$$\Theta(NLog(N))$$
***MULTIPLICATION COMBINES FACTORS; ADDITION IS DOMINATED BY THE FASTEST-GROWING TERM***
## Solution
### Simplify
- Expression1: $R(N) = Log(N) + N \in \Theta(N + Log(N))\in \Theta(N)$
- Expression2: $R(N) = Log(2^{N}) = Log(2) \times N \in \Theta(Log(2) \times N) \in \Theta(N)$ 
- Expression3: $R(N) = N^{2} + 3NLog(N) \in \Theta(N^{2} + 3NLog(N)) \in \Theta(N^{2} + NLog(N)) \in \Theta(N^{2})$ 
- Expression4: $R(N) = 2^{N} \times 2 \in \Theta(2 \times 2^{N}) \in \Theta(2^{N})$ 
- Expression5: $R(N) = Log(9) \in \Theta(1)$ 
- Expression6: $R(N) = 2^{N+4} = 2^{N} \times 2^{4} \in \Theta(2^{N} \times 2^{4}) \in \Theta(2^{N})$ 
- Expression7: $R(N) = 2 + \frac{1}{n} \in \Theta(2 + \frac{1}{n}) \in \Theta(2) \in \Theta(1)$ Constant (AS $N$ &rarr; $\infty$ : $\frac{1}{n}$ &rarr; $0$) 
- Expression8: $R(N) = Log(N^{2}) = 2 \times Log(N) \in \Theta(2 \times Log(N)) \in \Theta(Log(N))$ 
- Expression9: $R(N) = 5 \times N^{2} \in \Theta(5 \times N^{2}) \in \Theta(N^{2})$ 
- Expression10: $R(N) = 6 \times Log(N) \in \Theta(6 \times Log(N)) \in \Theta(Log(N))$
### Order
- Constant($\Theta(1)$):$R(N) = Log(9)$ AND $R(N) = 2 + \frac{1}{n}$
- Logarith($\Theta(Log(N))$):$R(N) = Log(N^{2})$ AND $R(N) = 6 \times Log(N)$
- Linear($\Theta(N)$): $R(N) = Log(N) + N$ AND $R(N) = Log(2^{N})$
- Quadratic($\Theta(N^{2})$): $R(N) = N^{2} + 3NLog(N)$ AND $R(N) = 5 \times N^{2}$ 
- Exponential($\Theta(2^{N})$): $R(N) = 2^{N} \times 2$ AND $R(N) = 2^{N+4}$
# 2 Thrown for a Loop

# 3 xStep

# 4 Disjoint Sets