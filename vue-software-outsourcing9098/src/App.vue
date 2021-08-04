<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  data() {
    return {

    };
  },
  methods: {
  }
};
</script>

<style lang="scss">
* {
  padding: 0;
  margin: 0;
}

html,
body {
  height: 100%;
}

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

</style>
