0. 刚开始`DubboApplication`类置于默认包下（即“没有包名”），这导致不能正常启动，提示信息是`WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter类找不到`，这个提示信息并不能真正揭示问题所在，使得定位问题花费了很多时间。最终结论：在生产环境中，所有类禁止置于默认包下
