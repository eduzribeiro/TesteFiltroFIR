%

accR  = load('../data/Gerados/Repouso/Aceleracao_Repouso.txt');
accRF = load('../data/Gerados/Repouso/AceleracaoFiltrada_Repouso.txt');


x1 = accR(:,1:1);
y1 = accR(:,2:2);

x2 = accRF(:,1:1);
y2 = accRF(:,2:2);

figure;
plot(x1-x1(1),y1,'g',x2-x2(1),y2,'b')


grid on

%axis([40,110,-0.15,0.20])

legend('Não Filtrada','Filtrada',0)

title('Acelarações Repouso Não Filtradas X Filtradas')

xlabel('Tempo')

ylabel('Medições')
