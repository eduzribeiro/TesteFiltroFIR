clear all
close all
clc

DA = load('C:\Users\Eduardo\Desktop\Diversos\UFLA\Mestrado\Dados TXT\Gerados\Atividade\Deslocamento_Atividade.txt')
DAF = load ('C:\Users\Eduardo\Desktop\Diversos\UFLA\Mestrado\Dados TXT\Gerados\Atividade\DeslocamentoFiltrado_Atividade.txt')

a = [140:210];
referencia1 = a';
referencia2 = a'*0;

x1 = DA(1:3000,1:1);
y1 = DA(1:3000,2:2);

x2 = DAF(1:3000,1:1);
y2 = DAF(1:3000,2:2);

plot(x1,y1,'g',referencia1,referencia2,'r',x2,y2,'b')


grid on

%axis([40,110,-0.15,0.20])

legend('Não Filtrado','Referência','Filtrado',0)

title('Deslocamento Atividade Não Filtrado X Filtrado')

xlabel('Tempo')

ylabel('Medições')
