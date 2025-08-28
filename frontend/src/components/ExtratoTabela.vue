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
  data() {
    return {
      agendamentos: []
    };
  },
  methods: {
    async carregarDados() {
      try {
        const response = await axios.get('http://localhost:8080/agendamentos');
        this.agendamentos = response.data;
      } catch (error) {
        console.error('Erro ao carregar os agendamentos:', error);
      }
    }
  },
  mounted() {
    // Carrega os dados quando o componente é montado
    this.carregarDados();
  }
};
</script>

<style scoped>
/* Estilos específicos para este componente */
</style>