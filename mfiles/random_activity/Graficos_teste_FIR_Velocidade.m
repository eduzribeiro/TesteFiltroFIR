%

DR  = load ('../../data/Gerados/random_activity/Velocidade_Repouso.txt');
DRF = load ('../../data/Gerados/random_activity/VelocidadeFiltrada_Repouso.txt');

a = [40:105];
referencia1 = a';
referencia2 = a'*0;

x1 = DR(:,1:1);
y1 = DR(:,2:2);

x2 = DRF(:,1:1);
y2 = DRF(:,2:2);

figure;
plot(x1-x1(1),y1,'g',x2-x2(1),y2,'b')


grid on

%axis([40,110,-0.15,0.20])

legend('Não Filtrado','Filtrado',0)

title('Velocidade Repouso Não Filtrado X Filtrado')

xlabel('Tempo')

ylabel('Medições')
