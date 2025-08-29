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
          <th scope="col">Ações</th>
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
          <td>
            <button @click="deletarAgendamento(agendamento.id)" class="btn btn-danger btn-sm">Cancelar</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ExtratoTabela',
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
        if (this.contaOrigem) {
          url = `${url}?contaOrigem=${this.contaOrigem}`;
        }
        const response = await axios.get(url);
        this.agendamentos = response.data;
      } catch (error) {
        console.error('Erro ao carregar os agendamentos:', error);
      }
    },
    async deletarAgendamento(id) {
      if (confirm('Tem certeza de que deseja cancelar este agendamento?')) {
        try {
          await axios.delete(`http://localhost:8080/agendamentos/${id}`);
          alert('Agendamento cancelado com sucesso!');
          this.carregarDados(); // Recarrega os dados para atualizar a tabela
        } catch (error) {
          console.error('Erro ao cancelar o agendamento:', error);
          alert('Erro ao cancelar o agendamento.');
        }
      }
    }
  },
  watch: {
    contaOrigem: {
      handler() {
        this.carregarDados();
      },
      immediate: true
    }
  }
};
</script>

<style scoped>
/* Estilos específicos para este componente */
</style>
