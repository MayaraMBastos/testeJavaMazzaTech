<template>
  <div id="app" class="container">
    <header class="text-center my-4">
      <h1>Sistema de Agendamento de Transferências</h1>
    </header>

    <main class="row">
      <section class="col-md-6 mb-4">
        <h2>Agendar Nova Transferência</h2>
        <AgendamentoFormulario @agendamento-salvo="atualizarExtrato" />
      </section>

      <section class="col-md-6">
        <h2>Extrato de Agendamentos</h2>
        <div class="mb-3">
          <label for="filtroConta" class="form-label">Filtrar por Conta de Origem</label>
          <input type="text" class="form-control" id="filtroConta" v-model="contaOrigemFiltro">
        </div>
        <!-- Passa a conta de origem do filtro como uma prop para a tabela -->
        <ExtratoTabela :contaOrigem="contaOrigemFiltro" ref="extratoComponent" />
      </section>
    </main>
  </div>
</template>

<script>
import AgendamentoFormulario from './components/AgendamentoFormulario.vue';
import ExtratoTabela from './components/ExtratoTabela.vue';

export default {
  name: 'App',
  components: {
    AgendamentoFormulario,
    ExtratoTabela,
  },
  data() {
    return {
      // Nova propriedade para o campo de filtro
      contaOrigemFiltro: null
    };
  },
  methods: {
    // Este método é chamado quando um agendamento é salvo com sucesso
    atualizarExtrato() {
      // Força a tabela a recarregar os dados do filtro atual
      this.$refs.extratoComponent.carregarDados();
    }
  }
};
</script>

<style>
#app {
  margin-top: 60px;
}
</style>
