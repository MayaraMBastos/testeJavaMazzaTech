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
          <td>{{ formatarData(agendamento.dataTransferencia) }}</td>
          <td>{{ formatarData(agendamento.dataAgendamento) }}</td>
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
    },
    formatarData(dataStr) {
      if (!dataStr) return '';
      const partes = dataStr.split('-');
      if (partes.length !== 3) return dataStr;
      const [ano, mes, dia] = partes;
      return `${dia.padStart(2, '0')}/${mes.padStart(2, '0')}/${ano}`;
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
.table {
  width: 100%;
  border-spacing: 0 0.5vw; /* Margem vertical menor entre as linhas */
  min-width: 600px;
  border: 1px solid #ccc;    /* Borda fina ao redor da tabela */
  border-collapse: separate; /* Não colapsa as bordas das células */
  border-radius: 6px;        /* Opcional: cantos arredondados */
  overflow: hidden;          /* Garante que o border-radius funcione */
}

th, td {
  padding: 1vw 0.5vw;
  vertical-align: middle;
  word-break: break-word;
  max-width: 120px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: center;
  border: none; /* Remove linhas internas */
}

th {
  font-size: 0.95em;
}

@media (max-width: 768px) {
  .table {
    font-size: 0.95em;
    min-width: 500px;
  }
  th, td {
    padding: 1.5vw 0.5vw;
    max-width: 80px;
  }
  th {
    font-size: 0.9em;
  }
}

@media (max-width: 576px) {
  .table {
    font-size: 0.9em;
    min-width: 400px;
  }
  th, td {
    padding: 2vw 0.5vw;
    max-width: 60px;
  }
  th {
    font-size: 0.85em;
  }
  :host, div {
    overflow-x: auto;
  }
}
</style>
