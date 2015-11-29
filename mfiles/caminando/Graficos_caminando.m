%

Acel = load('../../data/Gerados/caminando/AceleracaoFiltrada_caminando.txt');
Velo = load('../../data/Gerados/caminando/VelocidadeFiltrada_caminando.txt');
Desl = load('../../data/Gerados/caminando/DeslocamentoFiltrada_caminando.txt');


ta = Acel(:,1);
a  = Acel(:,2);

tv = Velo(:,1);
v  = Velo(:,2);

td = Desl(:,1);
d  = Desl(:,2);

figure;
subplot(3,1,1)
plot(ta-ta(1),a,'r');

subplot(3,1,2)
plot(tv-tv(1),v,'g');

subplot(3,1,3)
plot(td-td(1),d,'b');


print('grafico_caminando_quantizar_filtro.eps')
