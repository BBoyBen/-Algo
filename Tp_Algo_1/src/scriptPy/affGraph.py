# -*- coding: utf-8 -*-
"""
Created on Sat Feb 17 14:21:37 2018

@author: Benoit
"""

import networkx as nx
import matplotlib.pyplot as plt

g = nx.Graph()

sommet = open("src/texte/sommets.txt", "r")
aretes = open("src/texte/aretes.txt", "r")

nbSommet = int(sommet.readline())
nbAretes = int(sommet.readline())

for s in range(0,nbSommet): 
    g.add_node(s)

for i in range(0,nbAretes):
    origine = int(aretes.readline())
    extrem = int(aretes.readline())
    g.add_edge(origine, extrem)

pos = nx.shell_layout(g)
nx.draw_networkx_labels(g,pos,font_size=12)
nx.draw(g, pos)

plt.show()










