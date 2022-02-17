<script>
import { useRouter } from 'vue-router'

export default {
  name: 'AdminHome',

  created () {
    
    const router = useRouter()

    if(!localStorage.getItem('adminData')){
      router.push({ name: 'AdminAuth' })
    }
    else if(localStorage.getItem('adminData') != null){
      router.push({ name: 'AdminStaff' })
    }
    
  },
  methods: {
  
  }
}
</script>
