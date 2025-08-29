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
        <ExtratoTabela ref="extratoComponent" />
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
  methods: {
    atualizarExtrato() {
      // Chama o método 'carregarDados' do componente ExtratoTabela
      this.$refs.extratoComponent.carregarDados();
    },
  },
};
</script>

<style>
#app {
  margin-top: 60px;
}
</style>