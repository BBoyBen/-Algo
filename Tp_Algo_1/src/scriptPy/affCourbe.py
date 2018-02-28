# -*- coding: utf-8 -*-
"""
Created on Sat Feb 17 14:38:14 2018

@author: Benoit
"""

import matplotlib.pyplot as plt

#"C:/Users/Benoit/Documents/Cours L3_s6/Algo 3/Algo_Tp1/src/texte/tpsExecArbre.txt"
#"C:/Users/Benoit/Documents/Cours L3_s6/Algo 3/Algo_Tp1/src/texte/tpsExecBourrin.txt"

def tempsExec () :
    nbSommets = [10,20,30,40,60,70,80,90,100,110,120,130,140,150]
    tpsExecArbre = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,]
    tpsExecBourrin = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,]
        
    tpsArbre = open("src/texte/tpsExecArbre.txt", "r")
    tpsBourrin = open("src/texte/tpsExecBourrin.txt", "r")
       
    for i in range(0,14):
        tpsExecArbre[i] = int(tpsArbre.readline())/1000000
        tpsExecBourrin[i] = int(tpsBourrin.readline())/1000000
            
        
    plt.plot(nbSommets, tpsExecArbre)
    plt.plot(nbSommets, tpsExecBourrin)
    plt.legend(['Arbre','Bourrin'])
    plt.xlabel("nombre de sommets")
    plt.ylabel("temps d'éxécution (ms)")
    plt.title("Comparaison temps d'éxécution")
    plt.show()
    

tempsExec()