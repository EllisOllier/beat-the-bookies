# Beat The Bookies - Arbitrage

 Punters often try to beat the bookies via a process called arbitrage. Arbitrage is the process of guaranteeing a profit by betting between different bookmakers. 
 Consider fixture Man United vs Arsenal (decimal odds)
 
Bookmaker A offers: 2.50 (40.00%) / 3.30 (30.30%) / 2.70 (37.03%)

Bookmaker B offers:		?	    / 5.00 (20.00%) /      ?
 
A punter has £100 to gamble, they choose the following bets:
   - They put £40 on Bookie A for United.
   - They put £37.03 on Bookie A for Arsenal
   - They put £20 of Bookie B for a draw.

 They've gambled £40 + £20 + £37.03 = £97.03
 but each outcome returns £100. This is a guaranteed margin of 2.97.
 This concept of guaranteeing a profit is known as arbitrage.

For each task, you will be provided with two datasets. The smaller should act as a sanity check for the correctness of your solution. 
The second acts as a benchmark to test the performance of the solution.

### Task 1: Identify All Arbitrages
Write a function that receives data and identifies all arbitrage bets, calculates their margins and then sum them.

&#8203;* To avoid precision errors, each margin should be rounded to two decimal places before summing.

### Task 2: Highest Profit
Uh oh! The bookmakers have flagged your account as suspicious for placing too many bets! 
You decide to focus your efforts on finding the bet with the highest margin.

Write a function that identifies the arbitrage bet with the highest margin.

### Task 3: Top K Profit Arbitrage
Drat! Your bet was cancelled due to an issue with the fixture. You decide its best to spread your bets 
over the top K most profitable bets. The value of K will change with each dataset.

Write a function to find the top K arbitrage bets from the provided sets of data, and sum their margins.
Use the following values of k for the associated datasets:

task3_small.txt - k=3

task3_big.txt - k = 100