<template>
  <div class="mt-1 mb-1">
    <b-icon-star-fill variant="primary" />
    <b-icon-star-fill variant="primary" />
    <b-icon-star-fill variant="primary" />
    <b-icon-star-half variant="primary" />
    <b-icon-star variant="primary" />
    <a style="color: grey; font-style: italic; font-size: small" href="#"
      >{{ randomNumber() }} Reviews</a
    >
  </div>
</template>

<script>
export default {
  methods: {
     randomNumber : function(){
      return Math.floor(Math.random() * (10 - 1 + 1)) + 1;
    }
  }
};
</script>   
