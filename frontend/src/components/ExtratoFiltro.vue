<template>
  <div class="mb-3">
    <label for="filtroConta" class="form-label">Filtrar por Conta de Origem</label>
    <input
      type="text"
      class="form-control"
      id="filtroConta"
      :value="contaOrigem"
      @input="$emit('update:contaOrigem', $event.target.value)"
    >
  </div>
</template>

<script>
export default {
  name: 'ExtratoFiltro',
  props: {
    contaOrigem: String
  }
};
</script>

<style scoped>


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
  height: 2.2em;
  box-sizing: border-box;
  max-width: 220px; /* diminui o comprimento do campo */
}

.form-label,
.form-control {
  font-family: 'Roboto', Arial, Helvetica, sans-serif;
}

@media (max-width: 768px) {
  .mb-3 {
    margin-bottom: 3vw;
  }
  .form-control {
    padding: 1.5vw 1vw;
    height: 2.4em;
  }
}

@media (max-width: 576px) {
  .mb-3 {
    margin-bottom: 4vw;
  }
  .form-control {
    padding: 2vw 2vw;
    height: 2.6em;
  }
}
</style>