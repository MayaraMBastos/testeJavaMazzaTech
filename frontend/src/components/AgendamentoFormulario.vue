<template>
  <form @submit.prevent="agendarTransferencia">
    <div class="mb-3">
      <label for="contaOrigem" class="form-label">Conta de Origem</label>
      <input type="text" class="form-control" id="contaOrigem" v-model="form.contaOrigem" required>
    </div>
    
    <div class="mb-3">
      <label for="contaDestino" class="form-label">Conta de Destino</label>
      <input type="text" class="form-control" id="contaDestino" v-model="form.contaDestino" required>
    </div>
    
    <div class="mb-3">
      <label for="valorTransferencia" class="form-label">Valor</label>
      <input type="number" class="form-control" id="valorTransferencia" v-model.number="form.valorTransferencia" required step="0.01">
    </div>
    
    <div class="mb-3">
      <label for="dataTransferencia" class="form-label">Data da Transferência</label>
      <input type="date" class="form-control" id="dataTransferencia" v-model="form.dataTransferencia" required>
    </div>
    
    <button type="submit" class="btn btn-primary">Agendar</button>
  </form>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AgendamentoFormulario',
  data() {
    return {
      form: {
        contaOrigem: '',
        contaDestino: '',
        valorTransferencia: null,
        dataTransferencia: null
      }
    };
  },
  methods: {
    // Método de validação local
    validarContas() {
      if (this.form.contaOrigem.length !== 10 || this.form.contaDestino.length !== 10) {
        alert('A conta de origem e a conta de destino devem conter exatamente 10 dígitos.');
        return false;
      }
      return true;
    },

    async agendarTransferencia() {
      // Validação do lado do cliente antes de enviar a requisição
      if (!this.validarContas()) {
        return; // Interrompe a execução se a validação falhar
      }

      try {
        const response = await axios.post('http://localhost:8080/agendamentos', this.form);

        if (response.status === 201) {
          alert('Transferência agendada com sucesso!');
          this.$emit('agendamento-salvo');
          // A linha abaixo foi removida para não limpar o formulário
          // this.resetForm();
        }
      } catch (error) {
        if (error.response && error.response.status === 400) {
          // Acesso à chave correta 'mensagem'
          alert(error.response.data.mensagem || 'Erro ao agendar a transferência.');
        } else {
          alert('Ocorreu um erro na comunicação com a API.');
        }
      }
    },
    resetForm() {
      this.form = {
        contaOrigem: '',
        contaDestino: '',
        valorTransferencia: null,
        dataTransferencia: null
      };
    }
  }
};
</script>

<style scoped>
/* Estilos específicos para este componente */
</style>
