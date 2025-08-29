<template>
  <div id="app">
    <header class="header-centralizado">
      <h1>Sistema de Agendamento de Transferências</h1>
    </header>

    <main class="container-centralizado main-flex">
      <section class="section-flex">
        <h2>Agendar Nova Transferência</h2>
        <AgendamentoFormulario @agendamento-salvo="atualizarExtrato" />
      </section>

      <section class="section-flex section-extrato">
        <h2>Extrato de Agendamentos</h2>
        <ExtratoFiltro
          :contaOrigem="contaOrigemFiltro"
          @update:contaOrigem="contaOrigemFiltro = $event"
        />
        <ExtratoTabela :contaOrigem="contaOrigemFiltro" ref="extratoComponent" />
      </section>
    </main>
  </div>
</template>

<script>
import AgendamentoFormulario from './components/AgendamentoFormulario.vue';
import ExtratoTabela from './components/ExtratoTabela.vue';
import ExtratoFiltro from './components/ExtratoFiltro.vue';

export default {
  name: 'App',
  components: {
    AgendamentoFormulario,
    ExtratoTabela,
    ExtratoFiltro
  },
  data() {
    return {
      contaOrigemFiltro: null
    };
  },
  methods: {
    atualizarExtrato() {
      this.$refs.extratoComponent.carregarDados();
    }
  }
};
</script>

<style scoped>
html, body, #app {
  height: 100%;
  margin: 0;
  background-color: #f8f9fa;
}

#app {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
}

.header-centralizado {
  width: 100%;
  text-align: center;
  margin-top: 2rem;
  margin-bottom: 2rem;
}

.container-centralizado {
  width: 90%;
  max-width: 1200px;
  padding: 2rem;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

/* Novo estilo para exibir sections lado a lado */
.main-flex {
  display: flex;
  gap: 2rem;
  justify-content: space-between;
  flex-wrap: wrap;
}

.section-flex {
  flex: 1 1 0;
  min-width: 320px;
  display: flex;
  flex-direction: column;
}

/* Específico para a section de extrato ocupar 70% */
.section-extrato {
  max-width: 70%;
  flex-basis: 70%;
}

/* Responsivo para telas menores */
@media (max-width: 900px) {
  .main-flex {
    flex-direction: column;
    gap: 1.5rem;
  }
  .section-flex,
  .section-extrato {
    max-width: 100%;
    flex-basis: 100%;
  }
}
</style>
