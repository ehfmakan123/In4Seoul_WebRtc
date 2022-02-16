<script>
import { useRouter } from 'vue-router'

export default {
  name: 'AdminHome',

  created () {
    
    const router = useRouter()

    if(!localStorage.getItem('adminData')){
      router.push({ name: 'AdminAuth' })
    }
    
    
  },
  methods: {
  
  }
}
</script>
