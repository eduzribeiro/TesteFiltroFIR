%

DR  = load ('../data/Gerados/Repouso/Deslocamento_Repouso.txt');
DRF = load ('../data/Gerados/Repouso/DeslocamentoFiltrada_Repouso.txt');


x1 = DR(:,1:1);
y1 = DR(:,2:2);

x2 = DRF(:,1:1);
y2 = DRF(:,2:2);

figure;
plot(x1-x1(1),y1,'g',x2-x2(1),y2,'b')


grid on

%axis([40,110,-0.15,0.20])

legend('N�o Filtrado','Refer�ncia','Filtrado',0)

title('Deslocamento Repouso N�o Filtrado X Filtrado')

xlabel('Tempo')

ylabel('Medi��es')
