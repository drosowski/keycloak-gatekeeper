<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
import axios from "axios"

export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: "Hello, World!"
    }
  },
  mounted() {
    axios
      .get('https://127.0.0.1:30080/greeting?name=JUG%20Bielefeld', {headers: {'Authorization': 'Bearer ' + localStorage.getItem("vue-token")}})
      .then(response => (this.msg = response.content))
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
