<template>
  <div class="col-3 p-4">
    <div class="card shadow box bg-yellow">
      <div class="card-body p-4" data-bs-toggle="modal" data-bs-target="#detailModal">
        <p class="card-title mb-3"><strong>{{ post.title }}</strong></p>
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
  strong {
    font-weight: bold;
  }

  .box {
    width: 100%;
    height: 100%; 
    padding-bottom: 60%;
    border-radius: 1.5rem;
    /* border: 0rem */
  }
</style>
