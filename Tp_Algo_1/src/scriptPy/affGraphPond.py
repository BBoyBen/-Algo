# -*- coding: utf-8 -*-
"""
Created on Wed Feb 21 13:05:17 2018

@author: Benoit
"""

import networkx as nx
import matplotlib.pyplot as plt

g = nx.Graph()

sommet = open("src/texte/sommetsPond.txt", "r")
aretesPond = open("src/texte/aretesPond.txt", "r")

nbSommet = int(sommet.readline())
nbAretes = int(sommet.readline())

for s in range(0,nbSommet): 
    g.add_node(s)

for i in range(0,nbAretes):
    origine = int(aretesPond.readline())
    extrem = int(aretesPond.readline())
    poids = int(aretesPond.readline())
    g.add_edge(origine, extrem, weight=poids)


pos = nx.shell_layout(g)
nx.draw_networkx_labels(g,pos,font_size=12)
nx.draw_networkx_edge_labels(g, pos, label_pos=0.3)
nx.draw(g, pos)

plt.show()