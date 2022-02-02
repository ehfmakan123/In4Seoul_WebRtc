<template>
  <div>
    <h3>PostListItem {{ post.id }}</h3>
    <p><strong>{{ post.title }}</strong></p>
    <p>{{ post.content }}</p>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'PostListItem',
  components: {
  },
  props: ["post"],
  setup() {

  }
}
</script>
