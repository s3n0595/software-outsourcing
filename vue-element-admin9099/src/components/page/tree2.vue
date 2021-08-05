<template>
  <div>
    <el-row>
      <h4 style="padding:10px">基础可选择</h4>
<!--  load:加载子树数据的方法，仅当 lazy 属性为true 时生效-->
<!--  show-checkbox:节点是否可被选择-->
<!--  @check-change:节点被选中状态发生改变的回调-->
      <el-tree :props="props" :load="loadNode" lazy show-checkbox @check-change="handleCheckChange"></el-tree>
    </el-row>
    <el-row>
      <h4 style="padding:10px">懒加载</h4>
      <el-tree :props="props1" :load="loadNode1" lazy show-checkbox></el-tree>
    </el-row>
    <el-row>
      <h4 style="padding:10px">默认选中</h4>
<!--  :default-expanded-keys="[2, 3]":默认展开节点的key数组-->
<!--  :default-checked-keys="[5]":默认勾选的节点key数值集合-->
      <el-tree
        :data="data2"
        show-checkbox
        node-key="id"
        :default-expanded-keys="[2, 3]"
        :default-checked-keys="[5]"
        :props="defaultProps"
      ></el-tree>
    </el-row>
  </div>
</template>


<script>
export default {
  data() {
    return {
      props: {
        // 节点对象属性
        label: "name",
        // 子节点对象属性
        children: "zones"
      },
      props1: {
          label: 'name',
          children: 'zones',
          // 叶子节点
          isLeaf: 'leaf'
        },
      count: 1,
      data2: [{
          id: 1,
          label: '一级 1',
          children: [{
            id: 4,
            label: '二级 1-1',
            children: [{
              id: 9,
              label: '三级 1-1-1'
            }, {
              id: 10,
              label: '三级 1-1-2'
            }]
          }]
        }, {
          id: 2,
          label: '一级 2',
          children: [{
            id: 5,
            label: '二级 2-1'
          }, {
            id: 6,
            label: '二级 2-2'
          }]
        }, {
          id: 3,
          label: '一级 3',
          children: [{
            id: 7,
            label: '二级 3-1'
          }, {
            id: 8,
            label: '二级 3-2'
          }]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
    };
  },
  methods: {
    // data:传递给 data 属性的数组中该节点所对应的对象
    // checked:节点本身是否被选中
    // indeterminate:节点的子树中是否有被选中的节点
    handleCheckChange(data, checked, indeterminate) {
      console.log(data, checked, indeterminate);
    },
    handleNodeClick(data) {
      console.log(data);
    },
    loadNode1(node, resolve) {
        // node.level === 0:树状图最开始显示的数据
        if (node.level === 0) {
          return resolve([{ name: 'region' },{ name: '123'}]);
        }
        if (node.level > 1) return resolve([]);

        setTimeout(() => {
          const data = [{
            name: 'leaf',
            leaf: true
          }, {
            name: 'zone',
          }];

          resolve(data);
        }, 500);
      },
    loadNode(node, resolve) {
      if (node.level === 0) {
        return resolve([{ name: "region1" }, { name: "region2" }]);
      }
      if (node.level > 3) return resolve([]);

      var hasChild;
      if (node.data.name === "region1") {
        hasChild = true;
      } else if (node.data.name === "region2") {
        hasChild = false;
      } else {
        hasChild = Math.random() > 0.5;
      }

      setTimeout(() => {
        var data;
        if (hasChild) {
          data = [
            {
              name: "zone" + this.count++
            },
            {
              name: "zone" + this.count++
            }
          ];
        } else {
          data = [];
        }

        resolve(data);
      }, 500);
    }
  }
};
</script>
