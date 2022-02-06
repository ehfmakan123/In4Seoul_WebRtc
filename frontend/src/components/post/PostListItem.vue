<template>
  <div class="col-3 p-3">
    <div class="card" style="height: 15rem;">
      <div class="card-body" data-bs-toggle="modal" data-bs-target="#detailModal">
        <p class="card-title"><strong>{{ post.title }}</strong></p>
        <p class="card-text">{{ post.content }}</p>
        <!-- <a href="#" class="card-link">Card link</a> -->
      </div>
    </div>

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
<style scoped>
  strong {
    font-weight: bold;
  }
</style>
