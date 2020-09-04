0. 刚开始`DubboApplication`类置于默认包下（即“没有包名”），这导致不能正常启动，提示信息是`WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter类找不到`，这个提示信息并不能真正揭示问题所在，使得定位问题花费了很多时间。最终结论：在生产环境中，所有类禁止置于默认包下
0. `dubbo.registry.group=A`对应ZK中的`/A`；而`dubbo.provider.group=B`对应ZK中的`/dubbo/B`
0. `dubbo.application.dumpDirectory`对应的目录需要提前建好，否则会写入失败，可将其设置为日志目录，就无需自己建立该目录
