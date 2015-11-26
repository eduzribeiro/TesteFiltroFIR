%

accA = load('../data/Gerados/Atividade/Aceleracao_Atividade.txt');
accAF = load('../data/Gerados/Atividade/AceleracaoFiltrada_Atividade.txt');


x1 = accA(:,1:1);
y1 = accA(:,2:2);

x2 = accAF(:,1:1);
y2 = accAF(:,2:2);

figure;
plot(x1-x1(1),y1,'g',x2-x2(1),y2,'b')


grid on

%axis([140,210,-8.5,8])

legend('Não Filtrada','Filtrada',0)

title('Acelarações Atividade Não Filtradas X Filtradas')

xlabel('Tempo')

ylabel('Medições')
