<template>
  <div class="col-2 p-4 me-4">
    <div class="card shadow box-small bg-yellow" data-bs-toggle="modal" data-bs-target="#detailModal">
      <div class="card-body p-4">
        <p class="card-title mb-3 fw-bold">{{ post.title }}</p>
        <p class="card-text">{{ post.content }}</p>
      </div>
    </div>

  </div>
</template>

<script>

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

</style>
