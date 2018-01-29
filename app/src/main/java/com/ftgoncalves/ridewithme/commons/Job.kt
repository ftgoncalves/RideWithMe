package com.ftgoncalves.ridewithme.commons

import io.reactivex.Completable

interface Job<in Input> {
  fun bind(input: Input): Completable
}
