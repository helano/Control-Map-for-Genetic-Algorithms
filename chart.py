import xlrd
import matplotlib as mpl
from mpl_toolkits.mplot3d import Axes3D
import numpy as np
import matplotlib.pyplot as plt


file_location = "E:/configuracoes_AG.xls"
workbook = xlrd.open_workbook(file_location)
sheet = workbook.sheet_by_index(0)

populacao = []
crossover = []
torneio = []


for row in range(sheet.nrows):

    populacao.append(sheet.cell_value(row, 0))
    crossover.append(sheet.cell_value(row, 1))
    torneio.append(sheet.cell_value(row, 2))

mpl.rcParams['legend.fontsize'] = 9

fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')

ax.scatter(populacao, crossover, torneio, c='red')

ax.set_xlabel('POPULATION SIZE')
ax.set_ylabel('CROSS')
ax.set_zlabel('taxa de Pressão de seleção')

plt.show()