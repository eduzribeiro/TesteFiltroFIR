clear all
close all
clc

accA = load('C:\Users\Eduardo\Desktop\Diversos\UFLA\Mestrado\Dados TXT\Gerados\Atividade\Aceleracao_Atividade.txt');
accAF = load('C:\Users\Eduardo\Desktop\Diversos\UFLA\Mestrado\Dados TXT\Gerados\Atividade\AceleracaoFiltrada_Atividade.txt');

a = [140:210];
referencia1 = a';
referencia2 = a'*0;

x1 = accA(1:3000,1:1);
y1 = accA(1:3000,2:2);

x2 = accAF(1:3000,1:1);
y2 = accAF(1:3000,2:2);


plot(x1,y1,'g',referencia1,referencia2,'r',x2,y2,'b')


grid on

axis([140,210,-8.5,8])

legend('Não Filtrada','Referência','Filtrada',0)

title('Acelarações Atividade Não Filtradas X Filtradas')

xlabel('Tempo')

ylabel('Medições')
