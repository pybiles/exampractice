console.log("--------publicHeaderApp--------")

let publicHeaderApp = new Vue({
    el:"#publicHeaderApp",
    data:{
        productTypeList:[]
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
    },
    created(){
        this.initproductTypeList();
    }
})
