<template>
  <div class="chat-item">
    <div v-if="myUserName != chat.nickname" class="text-start">
      <p class="chat-item-header p-1">{{ chat.nickname }}</p>
      <div class="bg-white chat-box text-start d-inline-block p-2">
        <p class="">{{ chat.message }}</p>
      </div>
    </div>
    <div v-if="myUserName === chat.nickname" class="text-end">
      <!-- <p class="chat-item-header">{{ chat.nickname }}</p> -->
      <div class="bg-blue-3 chat-box text-start d-inline-block p-2">
        <p class="">{{ chat.message }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Chat',
  data () {
    return {
      myUserName: undefined,
    }
  },
  props: {
    chat: Object,
  },
  created () {
		const staffData = JSON.parse(localStorage.getItem('staffData'))
		const deskData = JSON.parse(localStorage.getItem('deskData'))

		if (staffData) {
			this.myUserName = staffData.name
    }
		
		if (deskData) {
			this.myUserName = deskData.deskKorName
		}

  }
}
</script>

<style>
.chat-item {
  margin-top: 1rem;
}

.chat-box {
  max-width: 50%;
  border-radius: 5px;
}

.chat-item-header {
  font-size: 0.8rem;
}

</style>