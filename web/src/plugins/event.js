// 消息总线，组件通信
import Vue from "vue";

const event = new Vue();

/**
 * 菜单收起
 */
export const publishMenuCollapse = collapse => {
    event.$emit("menuCollapse", collapse);
};

export const listenerMenuCollapse = fn => {
    event.$on('menuCollapse', data => fn(data));
};

