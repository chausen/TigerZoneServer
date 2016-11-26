#!/bin/bash
for i in 'seq 0 23';
  java EchoClient localhost 19000
done
