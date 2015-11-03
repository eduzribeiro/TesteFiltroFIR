clear all
close all
clc

DR = load('C:\Users\Eduardo\Desktop\Diversos\UFLA\Mestrado\Dados TXT\Gerados\Repouso\Deslocamento_Repouso.txt')
DRF = load ('C:\Users\Eduardo\Desktop\Diversos\UFLA\Mestrado\Dados TXT\Gerados\Repouso\DeslocamentoFiltrado_Repouso.txt')

a = [40:105];
referencia1 = a';
referencia2 = a'*0;

x1 = DR(1:3000,1:1);
y1 = DR(1:3000,2:2);

x2 = DRF(1:3000,1:1);
y2 = DRF(1:3000,2:2);

plot(x1,y1,'g',referencia1,referencia2,'r',x2,y2,'b')


grid on

%axis([40,110,-0.15,0.20])

legend('Não Filtrado','Referência','Filtrado',0)

title('Deslocamento Repouso Não Filtrado X Filtrado')

xlabel('Tempo')

ylabel('Medições')
