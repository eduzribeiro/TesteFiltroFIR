%

DA  = load ('../data/Gerados/Atividade/Deslocamento_Atividade.txt');
DAF = load ('../data/Gerados/Atividade/DeslocamentoFiltrada_Atividade.txt');

x1 = DA(:,1:1);
y1 = DA(:,2:2);

x2 = DAF(:,1:1);
y2 = DAF(:,2:2);

figure;
plot(x1,y1,'g',x2,y2,'b')


grid on

%axis([40,110,-0.15,0.20])

legend('N�o Filtrado','Refer�ncia','Filtrado',0)

title('Deslocamento Atividade N�o Filtrado X Filtrado')

xlabel('Tempo')

ylabel('Medi��es')
