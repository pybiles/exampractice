console.log("--------publicHeaderApp--------")

let publicHeaderApp = new Vue({
    el:"#publicHeaderApp",
    data:{
        productTypeList:[],
        currentUserUsername:''
    },
    methods:{
        initproductTypeList(){

            let vueObj = this;

            $.get("/productType/all",function (data){
                console.log(data)
                vueObj.productTypeList = data;
            })
        }
        ,
        seeProductsOfType(productTypeId){
            sessionStorage.setItem("seeProductsOfType",productTypeId)
            location.href = "/productsOfType.html"
        }
        ,
        initCurrentUserUsername(){
            let vueObj = this;
            $.post("/user/getCurrentUserUsername",function (data) {
                console.log("当前用户名 "+data)
                vueObj.currentUserUsername = data;
            })
        }
        ,
        toLogout(){
            $.post("/user/logout",function (data) {
                location.href="/"
            })
        }
    },
    created(){
        this.initproductTypeList();
        this.initCurrentUserUsername();
    }
})
