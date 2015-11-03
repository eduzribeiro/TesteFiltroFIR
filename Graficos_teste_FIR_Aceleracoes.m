clear all
close all
clc

accR = load('C:\Users\Eduardo\Desktop\Diversos\UFLA\Mestrado\Dados TXT\Gerados\Repouso\Aceleracao_Repouso.txt');
accRF = load('C:\Users\Eduardo\Desktop\Diversos\UFLA\Mestrado\Dados TXT\Gerados\Repouso\AceleracaoFiltrada_Repouso.txt');

a = [40:105];
referencia1 = a';
referencia2 = a'*0;

x1 = accR(1:3000,1:1);
y1 = accR(1:3000,2:2);

x2 = accRF(1:3000,1:1);
y2 = accRF(1:3000,2:2);


plot(x1,y1,'g',referencia1,referencia2,'r',x2,y2,'b')


grid on

%axis([40,110,-0.15,0.20])

legend('Não Filtrada','Referência','Filtrada',0)

title('Acelarações Repouso Não Filtradas X Filtradas')

xlabel('Tempo')

ylabel('Medições')
