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
      <input
        type="date"
        class="form-control"
        id="dataTransferencia"
        v-model="form.dataTransferencia"
        :min="hoje"
        required
      >
    </div>
    
    <button type="submit" class="btn btn-primary">Agendar</button>
  </form>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AgendamentoFormulario',
  data() {
    const hoje = new Date();
    const yyyy = hoje.getFullYear();
    const mm = String(hoje.getMonth() + 1).padStart(2, '0');
    const dd = String(hoje.getDate()).padStart(2, '0');
    return {
      hoje: `${yyyy}-${mm}-${dd}`,
      form: {
        contaOrigem: '',
        contaDestino: '',
        valorTransferencia: null,
        dataTransferencia: null,
        dataAgendamento: null
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

      // Define a data de agendamento como hoje (formato yyyy-MM-dd)
      const hoje = new Date();
      const yyyy = hoje.getFullYear();
      const mm = String(hoje.getMonth() + 1).padStart(2, '0');
      const dd = String(hoje.getDate()).padStart(2, '0');
      this.form.dataAgendamento = `${yyyy}-${mm}-${dd}`;

      try {
        const response = await axios.post('http://localhost:8080/agendamentos', this.form);

        if (response.status === 201) {
          alert('Transferência agendada com sucesso!');
          this.$emit('agendamento-salvo');
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
form {
  width: 100%;
  min-width: 280px;
  max-width: 500px;
  margin: 0 auto;
}

.mb-3 {
  margin-bottom: 2vw;
  display: flex;
  flex-direction: column;
}

.form-label {
  margin-bottom: 0.4vw;
  font-weight: 500;
  display: block;
  text-align: left;
}

.form-control {
  padding: 0.5vw 1vw;
  font-size: 1em;
  border-radius: 6px;
  border: 1px solid #ced4da;
  height: 2.2em; /* altura menor */
  box-sizing: border-box;
}

button[type="submit"] {
  margin-top: 2vw;
  padding: 1vw 3vw;
  font-size: 1em;
}

@media (max-width: 768px) {
  .mb-3 {
    margin-bottom: 3vw;
  }
  .form-control {
    padding: 1.5vw 1vw;
    height: 2.4em;
  }
  button[type="submit"] {
    padding: 2vw 5vw;
  }
}

@media (max-width: 576px) {
  form {
    min-width: 180px;
    max-width: 100%;
  }
  .mb-3 {
    margin-bottom: 4vw;
  }
  .form-control {
    padding: 2vw 2vw;
    height: 2.6em;
  }
  button[type="submit"] {
    padding: 3vw 8vw;
  }
}
</style>
