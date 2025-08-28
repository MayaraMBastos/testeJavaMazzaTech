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
    async agendarTransferencia() {
      try {
        const response = await axios.post('http://localhost:8080/agendamentos', this.form);
        
        if (response.status === 201) {
          alert('Transferência agendada com sucesso!');
          // Emite um evento para o componente pai (App.vue)
          this.$emit('agendamento-salvo');
          this.resetForm();
        }
      } catch (error) {
        if (error.response && error.response.status === 400) {
            // Lança um alerta sobre o erro e não permite a transferência 
          alert(error.response.data.message || 'Erro ao agendar a transferência.');
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