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