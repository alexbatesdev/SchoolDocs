import os
import pandas as pd

dirpath = 'SimplePysistence\Assignment 1 - data\people\long\\'
output = 'SimplePysistence\Assignment 1 - data\people\long.csv'
csvout_lst = []
files = [os.path.join(dirpath, fname) for fname in os.listdir(dirpath)]

for filename in sorted(files):
    data = pd.read_csv(filename, sep=',', index_col=0, header=None)
    csvout_lst.append(data)

pd.concat(csvout_lst).to_csv(output)