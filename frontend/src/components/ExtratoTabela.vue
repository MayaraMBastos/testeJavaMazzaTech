<template>
  <div>
    <table class="table table-striped">
      <thead>
        <tr>
          <th scope="col">Conta Origem</th>
          <th scope="col">Conta Destino</th>
          <th scope="col">Valor</th>
          <th scope="col">Taxa</th>
          <th scope="col">Data da Transferência</th>
          <th scope="col">Data de Agendamento</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="agendamento in agendamentos" :key="agendamento.id">
          <td>{{ agendamento.contaOrigem }}</td>
          <td>{{ agendamento.contaDestino }}</td>
          <td>R$ {{ agendamento.valorTransferencia.toFixed(2) }}</td>
          <td>R$ {{ agendamento.taxa.toFixed(2) }}</td>
          <td>{{ agendamento.dataTransferencia }}</td>
          <td>{{ agendamento.dataAgendamento }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ExtratoTabela',
  // Recebe a conta de origem como prop do App.vue
  props: ['contaOrigem'],
  data() {
    return {
      agendamentos: []
    };
  },
  methods: {
    async carregarDados() {
      try {
        let url = 'http://localhost:8080/agendamentos';
        // Adiciona o parâmetro de filtro se a conta de origem for fornecida
        if (this.contaOrigem) {
          url = `${url}?contaOrigem=${this.contaOrigem}`;
        }
        const response = await axios.get(url);
        this.agendamentos = response.data;
      } catch (error) {
        console.error('Erro ao carregar os agendamentos:', error);
      }
    }
  },
  // 'watcher' para monitorar a mudança na prop 'contaOrigem'
  watch: {
    contaOrigem: {
      // Omitimos o parâmetro 'newValue' porque não é usado, resolvendo o erro do ESLint
      handler() {
        this.carregarDados();
      },
      immediate: true // Executa o handler na primeira vez em que o componente é montado
    }
  }
};
</script>

<style scoped>
/* Estilos específicos para este componente */
</style>
